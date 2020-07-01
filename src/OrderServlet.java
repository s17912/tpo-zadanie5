import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet("/index")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/m.wrzesniewski/eclipse-workspace/TP05_WM_s17912/web/resources/cars.csv"));
        String line = "";

        List<CarModel> cars = new ArrayList<>();


        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            cars.add(new CarModel(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]));
        }


//        String s_brand =  request.getParameter( "brand" );
//       // String s_model =  request.getParameter( "brand" );
//        if(s_brand!=null){
//            System.out.println("will be filtered"+s_brand);
//            for (CarModel car:cars) {
//                if(!car.getBrand().equals(s_brand)){
//                    cars.remove(car);
//                }
//            }
//        }



        List<String> brands = cars.stream().map(CarModel::getBrand).distinct().collect(Collectors.toList());
        Collections.sort(brands, String.CASE_INSENSITIVE_ORDER);

        List<String> models = cars.stream().map(CarModel::getModel).collect(Collectors.toList());
        Collections.sort(models, String.CASE_INSENSITIVE_ORDER);

        request.setAttribute("brands", brands);
        request.setAttribute("models", models);
        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }


}
