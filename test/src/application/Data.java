package application;

import java.time.LocalDate;

public class Data {
    private final int year;
    private final int month;
    private final int day;

    
    public Data(int year, int month, int day) {
    	this.year = year;
        this.month = month;
        this.day  = day;
	}
    
    public int getYear() {
    	return year;
    }

	//controll your data
    public boolean isValid(){
        if(year < 0 || month < 0 || month > 12 || day < 0 || day > 31){
            return false;
        }
        if(month == 2){
            if(year % 4 == 0){
                if(day > 29){
                    return false;
                }
            }
            else{
                if(day > 28){
                    return false;
                }
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day > 30){
                return false;
            }
        }
        return true;
    }
    
    public int compareTo(Data oggi) {
    	
    	if(year != oggi.year)
    		return year -oggi.year;
    	
    	if(month != oggi.month)
    		return month-oggi.month;
    	
    	return day-oggi.day;
    	
    }
    
    public int  compareTo(LocalDate data) {
    	
    	if(year != data.getYear())
    		return year -data.getYear();
    	
    	if(month != data.getMonthValue())
    	   return month -data.getMonthValue();
    	
    	return day - data.getDayOfMonth();
    }
    
public Data CostruisciData(String text) {
		
		String giorno = text.substring(0,2);
		String mese = text.substring(3,5);
		String anno = text.substring(6, text.length());
		
		int day = Integer.valueOf(giorno);
		int month = Integer.valueOf(mese);
		int year = Integer.valueOf(anno);
		
		Data data = new Data(year,month,day);
		
		return data;
		
	}


}
