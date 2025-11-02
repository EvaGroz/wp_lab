package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "chefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(SpringTemplateEngine templateEngine, ChefService chefService, DishService dishService) {
        this.templateEngine = templateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        Long chefId = Long.parseLong(req.getParameter("chefid"));
        context.setVariable("izbranChef", this.chefService.findById(chefId));
        context.setVariable("listdishes", this.dishService.listDishes());
        templateEngine.process("chefDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dishId=req.getParameter("dishId");
        Long chefId= Long.parseLong(req.getParameter("chefId"));
        this.chefService.addDishToChef(chefId, dishId);
        resp.sendRedirect("/chefDetails?chefid="+chefId);
    }
}
