import java.util.Objects;

public abstract class Work {
    private String WorkName, DueDate, Status;
    private double WorkWeight;
    private int Grade;

    public Work(String name, String date, String status, double weight){
        this.WorkName = name;
        this.DueDate = date;
        this.Status = status;
        this.WorkWeight = weight;
        this.Grade = 0;
    }

    public String getName(){
        return WorkName;
    }

    public String getDueDate(){
        return DueDate;
    }

    public int getDueDateInt(){
        return Integer.parseInt(DueDate.replace("/", ""));
    }

    public String getStatus(){
        return Status;
    }

    @Override
    public String toString(){
        return getName() + ": " + getDueDate();
    }

    public double getWorkWeight(){
        return WorkWeight;
    }

    public int getGrade(){
        return Grade;
    }

    public void setGrade(int grade){
        Grade = grade;
    }

    public  void setStatus(String status){
        Status = status;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (obj != null){
            if(getClass() == obj.getClass()){
                Work other = (Work) obj;
                if(Objects.equals(other.getName(), this.getName())){
                    if(Objects.equals(other.getDueDate(), this.getDueDate())){
                        if(Objects.equals(other.getStatus(), this.getStatus())){
                            if(Objects.equals(other.getGrade(), this.getGrade())){
                                return Objects.equals(other.getWorkWeight(), this.getWorkWeight());
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
