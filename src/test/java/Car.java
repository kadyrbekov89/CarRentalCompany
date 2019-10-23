
import lombok.Data;




@Data
public class Car {

private String make;
private String model;
private String vin;
private Metadata metadata;
private PerDayRent perdayrent;
private Metrics metrics;




}
