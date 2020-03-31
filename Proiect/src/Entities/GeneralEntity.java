package Entities;

public abstract class GeneralEntity {
    protected int id;
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
