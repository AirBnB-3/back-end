package com.lambdaschool.airbnbbuildweek.models;

import javax.validation.constraints.Email;

public class UserMinimum
{
    private String username;

    private String firstname;

    private String lastname;

    private String password;

    @Email
    private String primaryemail;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPrimaryemail()
    {
        return primaryemail;
    }

    /**
     * Setter for email for this user
     *
     * @param primaryemail the new email address (String) for this user.
     */
    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail;
    }
}
