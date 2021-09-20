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

class Enrollee implements Comparable<Enrollee> {
  private String userId;
  private String firstName;
  private String lastName;
  private int version;
  private String insuranceCompany;

  // Constructor
  public Enrollee(String userId, String firstName, String lastName,
             int version, String insuranceCompany)
  {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.version = version;
    this.insuranceCompany = insuranceCompany;
  }

  // Comparator
  public int compareTo(Enrollee o) {
    if (this.getLastName().compareTo(o.getLastName()) != 0 )
    {
         return this.getLastName().compareTo(o.getLastName());
    }
    else
    {
         if (this.getFirstName().compareTo(o.getFirstName()) != 0)
         {
               return this.getFirstName().compareTo(o.getFirstName());
         }
         else
         {
               return o.getVersion() - this.getVersion();
         }
    }
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public void setVersion(int version)
  {
    this.version = version;
  }

  public void setInsuranceCompany(String insuranceCompany)
  {
    this.insuranceCompany = insuranceCompany;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getFirstName()
  {
    return this.firstName;
  }

  public String getLastName()
  {
    return this.lastName;
  }

  public int getVersion()
  {
    return this.version;
  }

  public String getInsuranceCompany()
  {
    return this.insuranceCompany;
  }

  public String toString()
  {
    return this.userId + "," + this.firstName + "," + this.lastName + ","
                + this.version + "," + this.insuranceCompany;
  }
}
