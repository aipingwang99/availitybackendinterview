import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;


class EnrollmentFile {


  // For each company, create a list of Enrollees based on records in input csv file
  private static HashMap<String, ArrayList<Enrollee> > hm = new HashMap<>();

  public static void readDataLineByLine(String file) throws IOException
  {
       BufferedReader br =
              new BufferedReader(new FileReader(file));

       try {
          String line = null;
          while ((line = br.readLine()) != null) {
            try
            {
          	   String[] arrOfStr = line.split(",");

          	   if (arrOfStr.length != 5)
               {
                 System.out.println("Bad record: " + line);
                 continue;
               }

               int version = Integer.parseInt(arrOfStr[3]);
               String insuranceCompany = arrOfStr[4];
               Enrollee enrollee = new Enrollee(arrOfStr[0], arrOfStr[1],
                            arrOfStr[2], version, insuranceCompany);
               System.out.println(enrollee);
               if (hm.containsKey(insuranceCompany))
               {
                   hm.get(insuranceCompany).add(enrollee);
               }
               else
               {
                   ArrayList<Enrollee> enrolleeList = new ArrayList<>();
                   enrolleeList.add(enrollee);
                   hm.put(insuranceCompany, enrolleeList);
               }
            }
            catch(Exception e)
            {
              System.out.println("Bad record: " + line);
            }
          }
        } finally {
             br.close();
        }
  }

  public static void writeDataToFiles(String path) throws IOException
  {
    for (Map.Entry<String, ArrayList<Enrollee> > e : hm.entrySet())
    {
      File file = new File(path + e.getKey() + ".csv");

      if (!file.exists()) {
          file.createNewFile();
      }

      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
    	try {


            ArrayList<Enrollee> list = e.getValue();
            Collections.sort(list);

            String record = null;
            String idFirstLast = null;
            for (Enrollee en : list)
            {
                String currIdFirstLast = en.getUserId() + ","
                         + en.getFirstName() + "," + en.getLastName();

                // For each company, the list is sorted based on last name and
                // first name in ascending order and version in descending order,
                // then skip all versions after the highest version
                if (idFirstLast != null && idFirstLast.equals(currIdFirstLast)) continue;

                idFirstLast = currIdFirstLast;
                record = currIdFirstLast + "," + en.getVersion() + ","
                          + en.getInsuranceCompany();
                bw.write(record + "\n");

              }
        }
        catch(Exception ex)
        {
              ex.printStackTrace();
        }
        finally
        {
              bw.close();
        }
     }
  }

  public static void main(String[] args) {
    String CSV_FILE_PATH = args[0];
    try {
      	String file = "Enrollees.csv";

    	  readDataLineByLine(CSV_FILE_PATH  + file);
      	writeDataToFiles(CSV_FILE_PATH);
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
  }
}
