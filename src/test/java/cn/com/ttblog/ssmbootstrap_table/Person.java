package cn.com.ttblog.ssmbootstrap_table;
public class Person
{
    private final Integer personID;
    private final String personName;
    public Person( Integer personID, String personName )
    {
        this.personID = personID;
        this.personName = personName;
    }
    public Integer getPersonID()
    {
        return personID;
    }
    public String getPersonName()
    {
        return personName;
    }
}