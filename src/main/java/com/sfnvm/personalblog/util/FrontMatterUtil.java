package com.sfnvm.personalblog.util;

import com.sfnvm.personalblog.model.FrontMatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class FrontMatterUtil {

  public Map<String, String> extract(String input) {
    try {
      val br = new BufferedReader(new StringReader(input));
      var line = br.readLine();

      while (line.isEmpty()) {
        line = br.readLine();
      }

      if (!line.matches("-{3,}")) {
        return new HashMap<>();
      }
      final String delimiter = line;

      HashMap<String, String> rs = new HashMap<>();
      line = br.readLine();
      while (!line.equals(delimiter)) {
        rs.put(
            line.substring(0, line.indexOf(":")).toLowerCase(),
            StringUtils.strip(line.substring(line.lastIndexOf(":") + 1).trim(), "\"")
        );
        line = br.readLine();
      }

      return rs;
    } catch (IOException ignored) {
      return new HashMap<>();
    }
  }

  //////////////////////////
  ///// CUSTOM BUILDER /////
  //////////////////////////

  public FrontMatter extractAndBuild(String input) {
    return extractAndBuild(extract(input));
  }

  /**
   * @param input should contain ["title", "date", "catergory"]
   */
  public FrontMatter extractAndBuild(Map<String, String> input) {
    val title = input.getOrDefault("title", "[unset]");
    val nullablePublishedDate = input.getOrDefault("date", null);
    val publishedDate = nullablePublishedDate == null
        ? Instant.now()
        : LocalDate.parse(
                nullablePublishedDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant();

    return FrontMatter.builder()
        .title(title)
        .publishedDate(publishedDate)
        .build();
  }
}
