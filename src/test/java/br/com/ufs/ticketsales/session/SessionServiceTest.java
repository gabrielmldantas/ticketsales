package br.com.ufs.ticketsales.session;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.ufs.ds3.bean.SessionModelBean;
import br.com.ufs.ds3.dao.EventDao;
import br.com.ufs.ds3.dao.SessionDao;
import br.com.ufs.ds3.entity.Event;
import br.com.ufs.ds3.entity.SessionType;
import br.com.ufs.ds3.entity.WeekDay;
import br.com.ufs.ds3.exception.TicketSalesException;
import br.com.ufs.ds3.service.SessionService;
import br.com.ufs.ds3.service.TheatreService;

public class SessionServiceTest {
	
	private SessionService sessionService;
	
	@Before
	public void init() {
		SessionDao sessionDao = Mockito.mock(SessionDao.class);
		TheatreService theatreService = Mockito.mock(TheatreService.class);
		this.sessionService = new SessionService(sessionDao, theatreService, Mockito.mock(EventDao.class));
	}
	
	@Test(expected = TicketSalesException.class)
	public void persistValidatesSessionEvent() {
		SessionModelBean sessionModelBean = new SessionModelBean(null, WeekDay.FRI, new Date(), SessionType.NUMBERED_CHAIR);
		sessionService.createSessions(sessionModelBean);
	}
	
	@Test(expected = TicketSalesException.class)
	public void persistValidatesSessionDay() {
		SessionModelBean sessionModelBean = new SessionModelBean(new Event(), null, new Date(), SessionType.NUMBERED_CHAIR);
		sessionService.createSessions(sessionModelBean);
	}

	@Test(expected = TicketSalesException.class)
	public void persistValidatesSessionStartHour() {
		SessionModelBean sessionModelBean = new SessionModelBean(new Event(), WeekDay.FRI, null, SessionType.NUMBERED_CHAIR);
		sessionService.createSessions(sessionModelBean);
	}

	@Test(expected = TicketSalesException.class)
	public void persistValidatesSessionType() {
		SessionModelBean sessionModelBean = new SessionModelBean(new Event(), WeekDay.FRI, new Date(), null);
		sessionService.createSessions(sessionModelBean);
	}
}
