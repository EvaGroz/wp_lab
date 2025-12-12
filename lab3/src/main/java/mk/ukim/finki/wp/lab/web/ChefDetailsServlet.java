package mk.ukim.finki.wp.lab.web;

/*import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "chefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final DishService dishService;
    private final ChefService chefService;
    private final SpringTemplateEngine templateEngine;
    
    public ChefDetailsServlet(DishService dishService, ChefService chefService, SpringTemplateEngine templateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JakartaServletWebApplication webApp = JakartaServletWebApplication.buildApplication(req.getServletContext());
        IWebExchange webExchange = webApp.buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        Chef chef = chefService.findById(chefId);
        Dish dish = dishService.findByDishId(dishId);
        chef.getDishes().add(dish);
        context.setVariable("chef", chef);
        List<Dish> dishes = chef.getDishes();
        context.setVariable("dishes", dishes);
        templateEngine.process("chefDetails.html", context, resp.getWriter());
    }
}


 */