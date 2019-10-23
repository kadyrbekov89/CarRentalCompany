import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Questions {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = RestAssured.get("https://rentalcars.com/cars/all");
        RentalResponse rentalResponse = mapper.readValue(response.asString(), RentalResponse.class);


        //TODO Question 1:
        // HERE WE WILL ITERATE THROUGH RENTAL RESPONSE AND DEFINE THE CAR MAKE BY IF CONDITON.
        // IF THE CONDITIONS ARE MET, IT WILL PRINT OUT THE MAKE AND  NOTES
        for (Car car : rentalResponse.getCars()) {
            if (car.getMake().equalsIgnoreCase("Tesla") &&
                    car.getMetadata().getColor().equalsIgnoreCase("Blue"))
                System.out.println(car.getMake() + " " + car.getMetadata().getNotes());
        }
    }


    //TODO Question 2:
    // HERE IN THIS METHOD WE WILL FIND CARS AND RETURN THOSE WHICH IS ACCORDING TO OUR
    // CONDITION MUST BE THE ONE THAT IS CHEAPER IN PRICE AND  DISCOUNT.


    public static List<Car> lowestCostCars(RentalResponse rentalResponse) {
        ArrayList<Car> list = new ArrayList<Car>();
        for (Car car : rentalResponse.getCars()) {
            if (car.getPerdayrent().getPrice() < 140 && car.getPerdayrent().getDiscount() > 15) {
                list.add(car);
            }
        }
        return list;

    }


    //TODO Question 3:
    // THIS METHOD CALLED 'BEST REVENUE CAR' HELPS US TO FIND THE HIGHEST REVENUE GENERATING CAR. ALSO CREATED
    // A NEW CAR OBJECT FROM CAR TO DEFINE AND RETURN IT IF THE ALL CONDITIONS ARE MET.
    // I CREATED VARIABLE "LOW" AND ASSIGN THE FOLLOWING VALUES AND ITERATING THROUGH RESPONSE AND SETTING
    // THE CONDITIONS BELOW IN IF STATEMENT AND ASSIGN IT TO NEW OBJECT AND RETURN IT.
    //


    public static Car bestRevenueCar(RentalResponse rentalResponse) {

            float low = rentalResponse.getCars().get(0).getPerdayrent().getPrice() *
                    rentalResponse.getCars().get(0).getMetrics().getRentalCounts().getYeartodate() -
                    rentalResponse.getCars().get(0).getMetrics().getYoymaintenancecost() -
                    rentalResponse.getCars().get(0).getMetrics().getDepreciation();

            Car bestCar = new Car();

            for (Car car : rentalResponse.getCars()) {
                if (low > car.getPerdayrent().getPrice() *
                        car.getMetrics().getRentalCounts().getYeartodate() -
                        car.getMetrics().getYoymaintenancecost() -
                        car.getMetrics().getDepreciation())
                        bestCar = car;
            }
            return bestCar;

        }
    }

