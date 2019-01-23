package com.example.cian.testproject.Model;


import com.example.cian.testproject.Model.Position;

public class Station {


    private String available_bikes;

    private String available_bike_stands;

    private String bike_stands;

    private Position position;

    private String last_update;

    private String contract_name;

    private String status;

    private String address;

    private String name;

    private String banking;

    private String bonus;

    private String number;

    public String getAvailable_bikes ()
    {
        return available_bikes;
    }

    public void setAvailable_bikes (String available_bikes)
    {
        this.available_bikes = available_bikes;
    }

    public String getAvailable_bike_stands ()
    {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands (String available_bike_stands)
    {
        this.available_bike_stands = available_bike_stands;
    }

    public String getBike_stands ()
    {
        return bike_stands;
    }

    public void setBike_stands (String bike_stands)
    {
        this.bike_stands = bike_stands;
    }

    public Position getPosition ()
    {
        return position;
    }

    public void setPosition (Position position)
    {
        this.position = position;
    }

    public String getLast_update ()
    {
        return last_update;
    }

    public void setLast_update (String last_update)
    {
        this.last_update = last_update;
    }

    public String getContract_name ()
    {
        return contract_name;
    }

    public void setContract_name (String contract_name)
    {
        this.contract_name = contract_name;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getBanking ()
    {
        return banking;
    }

    public void setBanking (String banking)
    {
        this.banking = banking;
    }

    public String getBonus ()
    {
        return bonus;
    }

    public void setBonus (String bonus)
    {
        this.bonus = bonus;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "[available_bikes = "+available_bikes+", available_bike_stands = "+available_bike_stands+", bike_stands = "+bike_stands+", position = "+position+", last_update = "+last_update+", contract_name = "+contract_name+", status = "+status+", address = "+address+", name = "+name+", banking = "+banking+", bonus = "+bonus+", number = "+number+"]";
    }
}
