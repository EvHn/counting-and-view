package example.view.web;

import example.view.data.CountingResult;
import example.view.data.Order;
import example.view.services.IViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class ViewServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletResponse response;
    @Mock
    private IViewService service;
    @InjectMocks
    private ViewServlet servlet;

    @BeforeEach
    public void setUp() {
        servlet = new ViewServlet();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doGet_OK_ORDER_ASC() throws IOException, ServletException {
        when(request.getParameter("order")).thenReturn(Order.Asc.name());
        List<CountingResult> results = new LinkedList<>();
        results.add(new CountingResult(1, "Cat", 13, new Date(10000L)));
        results.add(new CountingResult(2, "Cat", 13, new Date(100000L)));
        when(service.getCountingResults(Order.Asc)).thenReturn(results);
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);
        verify(response, never()).sendError(anyInt(), anyString());
        verify(service, atLeastOnce()).getCountingResults(Order.Asc);
        verify(session, atLeastOnce()).setAttribute("countingResults", results);
        verify(response, atLeastOnce()).sendRedirect("/view/countingResult.jsp");
    }

    @Test
    void doGet_OK_ORDER_DESC() throws IOException, ServletException {
        when(request.getParameter("order")).thenReturn(Order.Desc.name());
        List<CountingResult> results = new LinkedList<>();
        results.add(new CountingResult(1, "Cat", 13, new Date(100000L)));
        results.add(new CountingResult(2, "Cat", 13, new Date(10000L)));
        when(service.getCountingResults(Order.Desc)).thenReturn(results);
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);
        verify(response, never()).sendError(anyInt(), anyString());
        verify(service, atLeastOnce()).getCountingResults(Order.Desc);
        verify(session, atLeastOnce()).setAttribute("countingResults", results);
        verify(response, atLeastOnce()).sendRedirect("/view/countingResult.jsp");
    }

    @Test
    void doGet_WRONG_ORDER_TYPE() throws IOException, ServletException {
        when(request.getParameter("order")).thenReturn("kek");
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);
        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_BAD_REQUEST,
                String.format(ViewServlet.WRONG_ORDER, "kek"));
        verify(service, never()).getCountingResults(Order.Desc);
        verify(session, never()).setAttribute(anyString(), anyString());
        verify(response, never()).sendRedirect(anyString());
    }
}