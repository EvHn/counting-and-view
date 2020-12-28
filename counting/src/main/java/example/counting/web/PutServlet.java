package example.counting.web;

import example.counting.data.CountingResult;
import example.counting.data.Type;
import example.counting.services.IMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Endpiont for receiving counting results
 * @author evgkhan
 */
@WebServlet("/put")
public class PutServlet extends HttpServlet {

    public static final String WRONG_TYPE = "Wrong parameter type %s";
    public static final String WRONG_COUNT = "Wrong parameter count %s";
    public static final String WRONG_DATE = "Wrong parameter date %s";
    public static final String WRONG_YEAR = "Max possible year 9999";

    @Inject
    private IMsgService msgService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeStr = req.getParameter("type");
        Type type;
        try {
            type = Type.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format(WRONG_TYPE, typeStr));
            return;
        }

        String countStr = req.getParameter("count");
        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format(WRONG_COUNT, countStr));
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = req.getParameter("date");
        Date date;
        try {
            date = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format(WRONG_DATE, dataStr));
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(9999, Calendar.DECEMBER, 31);
        if(date.after(calendar.getTime())) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, WRONG_YEAR);
            return;
        }
        msgService.sendCountingResult(new CountingResult(type, count, date));
        resp.sendRedirect("/counting");
    }


}
