import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Reports {
    jsonReports Rep = new jsonReports();
    List<objReport> report = Rep.get();
    jsonTwittes Get = new jsonTwittes();
    List<objTwitte> twittes = Get.get();
    int numtwitte;
    Text text = new Text();
    File f = new File("Reports.json");
    private static final Logger logger = LogManager.getLogger(Reports.class);
    public Reports(String reporter, String reptext, int[] mapper, String response, int counter) {
        logger.info("System: user went to Reports");

        try {
            numtwitte = Integer.parseInt(response.substring(1));
            if (numtwitte >= counter)
                text.printer("\nPlease insert command as a valid format!", ConsoleColors.RED_BOLD_BRIGHT);
            else {
                if (f.exists()) {
                    report.add(new objReport(twittes.get(mapper[numtwitte - 1]).getSerial()
                            , reporter, twittes.get(mapper[numtwitte - 1]).getText()
                            , twittes.get(mapper[numtwitte - 1]).getSender(), reptext));

                } else {
                    report = Collections.singletonList(
                            new objReport(twittes.get(mapper[numtwitte - 1]).getSerial()
                                    , reporter, twittes.get(mapper[numtwitte - 1]).getText()
                                    , twittes.get(mapper[numtwitte - 1]).getSender(), reptext));
                }
                new jsonReports(report);
                logger.info("System: Twitte reported");
                text.printer("Twitte reported !", ConsoleColors.RED_BOLD_BRIGHT);

            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            text.printer("\nPlease insert command as a valid format!", ConsoleColors.RED_BOLD_BRIGHT);
        }


    }
}
