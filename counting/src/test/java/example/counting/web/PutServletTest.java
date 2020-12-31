package example.counting.web;

import example.counting.data.CountingResult;
import example.counting.data.Type;
import example.counting.services.IMsgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class PutServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private IMsgService msgService;
    @InjectMocks
    private PutServlet putServlet;

    @BeforeEach
    public void setUp() {
        putServlet = new PutServlet();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doPost_OK() throws ServletException, IOException, ParseException {
        when(request.getParameter("type")).thenReturn(Type.Cat.name());
        when(request.getParameter("count")).thenReturn("13");
        when(request.getParameter("date")).thenReturn("2020-12-31");

        putServlet.doPost(request, response);
        verify(response, never()).sendError(anyInt(), anyString());
        verify(msgService, atLeastOnce()).sendCountingResult(new CountingResult(Type.Cat, 13,
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31")));
        verify(response, atLeastOnce()).sendRedirect("/counting");
    }

    @Test
    void doPost_WRONG_TYPE() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn("Track");
        when(request.getParameter("count")).thenReturn("13");
        when(request.getParameter("date")).thenReturn("2020-12-31");

        putServlet.doPost(request, response);
        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_BAD_REQUEST,
                String.format(PutServlet.WRONG_TYPE, "Track"));
        verify(msgService, never()).sendCountingResult(any(CountingResult.class));
        verify(response, never()).sendRedirect("/counting");
    }

    @Test
    void doPost_WRONG_COUNT() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn(Type.Cat.name());
        when(request.getParameter("count")).thenReturn(null);
        when(request.getParameter("date")).thenReturn("2020-12-31");

        putServlet.doPost(request, response);
        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_BAD_REQUEST,
                String.format(PutServlet.WRONG_COUNT, null));
        verify(msgService, never()).sendCountingResult(any(CountingResult.class));
        verify(response, never()).sendRedirect("/counting");
    }

    @Test
    void doPost_WRONG_DATE() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn(Type.Cat.name());
        when(request.getParameter("count")).thenReturn("13");
        when(request.getParameter("date")).thenReturn("");

        putServlet.doPost(request, response);
        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_BAD_REQUEST,
                String.format(PutServlet.WRONG_DATE, ""));
        verify(msgService, never()).sendCountingResult(any(CountingResult.class));
        verify(response, never()).sendRedirect("/counting");
    }

    @Test
    void doPost_WRONG_YEAR() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn(Type.Stone.name());
        when(request.getParameter("count")).thenReturn("13");
        when(request.getParameter("date")).thenReturn("1231233-12-31");

        putServlet.doPost(request, response);
        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_BAD_REQUEST, PutServlet.WRONG_YEAR);
        verify(msgService, never()).sendCountingResult(any(CountingResult.class));
        verify(response, never()).sendRedirect("/counting");
    }

}