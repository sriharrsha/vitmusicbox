package com.sriharrsha.musicbox.helpers;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.slf4j.*;

@WebFilter(urlPatterns = { "/*" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR })
public class HibernateServletFilter implements Filter
{
    private static final Logger logger = LoggerFactory.getLogger(HibernateServletFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        logger.debug("Initializing HibernateServletFilter");
    }

    @Override
    public void destroy()
    {
        logger.debug("Destroying HibernateServletFilter");
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        if (session.getTransaction().isActive())
        {
            logger.debug("Committing the final active transaction");
            session.getTransaction().commit();
        }

        if (session.isOpen())
        {
            logger.debug("Closing the final open session");
            session.close();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try
        {
            logger.debug("Starting a database transaction");
            session.beginTransaction();
            
            chain.doFilter(request, response);
            
            logger.debug("Committing the active database transaction");
            session.getTransaction().commit();
        }
        catch (StaleObjectStateException e)
        {
            logger.error(e.toString());

            if (session.getTransaction().isActive())
            {
                logger.debug("Rolling back the active transaction");
                session.getTransaction().rollback();
            }
            
            throw e;
        }
        catch (Throwable e)
        {
            logger.error(e.toString());
            
            if (session.getTransaction().isActive())
            {
                logger.debug("Rolling back the active transaction");
                session.getTransaction().rollback();
            }
            
            throw new ServletException(e);
        }
    }
}