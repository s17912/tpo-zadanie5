import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        List<String> types = new ArrayList<>();
        List<String> brands = new ArrayList<>();
        List<String> models = new ArrayList<>();

        String s_type = request.getParameter("type");
        if (s_type != null && s_type.length() != 0) {
            cars.removeIf(c -> !c.getType().equals(s_type));
        }

        String s_brand = request.getParameter("brand");
        if (s_brand != null && s_brand.length() != 0) {
            cars.removeIf(c -> !c.getBrand().equals(s_brand));
        }

        String s_model = request.getParameter("model");
        if (s_model != null && s_model.length() != 0) {
            cars.removeIf(c -> !c.getModel().equals(s_model));
        }

        types = cars.stream().map(CarModel::getType).distinct().collect(Collectors.toList());
        Collections.sort(types, String.CASE_INSENSITIVE_ORDER);

        brands = cars.stream().map(CarModel::getBrand).distinct().collect(Collectors.toList());
        Collections.sort(brands, String.CASE_INSENSITIVE_ORDER);

        models = cars.stream().map(CarModel::getModel).distinct().collect(Collectors.toList());
        Collections.sort(models, String.CASE_INSENSITIVE_ORDER);

        request.setAttribute("types", types);
        request.setAttribute("brands", brands);
        request.setAttribute("models", models);
        request.setAttribute("cars", cars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }


}
