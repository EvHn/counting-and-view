package example.view.web;

import example.view.services.IViewService;
import example.view.data.Order;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Endpoint for getting sorted counting results
 * @author evgkhan
 */
@WebServlet("/countingResults")
public class ViewServlet extends HttpServlet {

    public static final String WRONG_ORDER = "Wrong order type %s";
    @Inject
    private IViewService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderStr = req.getParameter("order");
        Order order;
        try {
            order = Order.valueOf(orderStr);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format(WRONG_ORDER, orderStr));
            return;
        }

        req.getSession().setAttribute("countingResults", service.getCountingResults(order));
        resp.sendRedirect("/view/countingResult.jsp");
    }
}

