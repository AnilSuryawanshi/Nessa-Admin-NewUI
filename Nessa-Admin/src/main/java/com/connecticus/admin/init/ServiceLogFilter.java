package com.connecticus.admin.init;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.connecticus.admin.model.ServiceLogModel;
import com.connecticus.admin.service.AdminService;



@Component("ServiceLogFilter")
public class ServiceLogFilter implements Filter {
	
	@Autowired
	AdminService adminService;
	
	final Logger logger = Logger.getLogger(ServiceLogFilter.class);

	private static class ByteArrayServletStream extends ServletOutputStream {

		ByteArrayOutputStream baos;

		ByteArrayServletStream(ByteArrayOutputStream baos) {
			this.baos = baos;
		}

		public void write(int param) throws IOException {
			baos.write(param);
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener listener) {
			// TODO Auto-generated method stub
		}
	}

	private static class ByteArrayPrintWriter {

		private ByteArrayOutputStream baos = new ByteArrayOutputStream();

		private PrintWriter pw = new PrintWriter(baos);

		private ServletOutputStream sos = new ByteArrayServletStream(baos);

		public PrintWriter getWriter() {
			return pw;
		}

		public ServletOutputStream getStream() {
			return sos;
		}

		byte[] toByteArray() {
			return baos.toByteArray();
		}
	}

	private class BufferedServletInputStream extends ServletInputStream {

		ByteArrayInputStream bais;

		public BufferedServletInputStream(ByteArrayInputStream bais) {
			this.bais = bais;
		}

		public int available() {
			return bais.available();
		}

		public int read() {
			return bais.read();
		}

		public int read(byte[] buf, int off, int len) {
			return bais.read(buf, off, len);
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			// TODO Auto-generated method stub

		}
	}

	private class BufferedRequestWrapper extends HttpServletRequestWrapper {

		ByteArrayInputStream bais;
		ByteArrayOutputStream baos;
		BufferedServletInputStream bsis;
		byte[] buffer;

		public BufferedRequestWrapper(HttpServletRequest req) throws IOException {
			super(req);
			InputStream is = req.getInputStream();
			baos = new ByteArrayOutputStream();
			byte buf[] = new byte[1024];
			int letti;
			while ((letti = is.read(buf)) > 0) {
				baos.write(buf, 0, letti);
			}
			buffer = baos.toByteArray();
		}

		public ServletInputStream getInputStream() {
			try {
				bais = new ByteArrayInputStream(buffer);
				bsis = new BufferedServletInputStream(bais);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return bsis;
		}

		public byte[] getBuffer() {
			return buffer;
		}
	}

	private boolean dumpRequest;
	private boolean dumpResponse;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		dumpRequest = true;
		dumpResponse = true;
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);

		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();

		autowireCapableBeanFactory.configureBean(this, "generateotpSevice");

	}
     //execute after response
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		// TODO Auto-generated method stubIne
		 
		try {
			//get Inet address
			InetAddress ipAddr;
			ipAddr = InetAddress.getLocalHost();
			int port = servletRequest.getServerPort();
			String portNo = String.valueOf(port);
			String ipAddress = ipAddr.getHostAddress();
			
			
			//System.out.println("#######################3  ipAddress"+ipAddress);
			String taskName = servletRequest.getParameter("taskName");
			String nessaQuestion = servletRequest.getParameter("nessaQuestion");
			String userUtterance = servletRequest.getParameter("user_name");
			String serviceName = servletRequest.getParameter("serviceName");
			String applicationName = servletRequest.getParameter("applicationName");
			String questionList = servletRequest.getParameter("nessaQuestionList");
			String answerList = servletRequest.getParameter("answerList");

			if (servletRequest.getParameter("user_name") != null) {
				userUtterance = servletRequest.getParameter("user_name");
			} else if (servletRequest.getParameter("userName") != null) {
				userUtterance = servletRequest.getParameter("userName");
			} else if (servletRequest.getParameter("phoneno") != null) {
				userUtterance = servletRequest.getParameter("phoneno");
			} else if (servletRequest.getParameter("urgency") != null) {
				userUtterance = servletRequest.getParameter("urgency");
			} else if (servletRequest.getParameter("incidentId") != null) {
				userUtterance = servletRequest.getParameter("incidentId");
			} else if (servletRequest.getParameter("assignGroupName") != null) {
				userUtterance = servletRequest.getParameter("assignGroupName");
			} else if (servletRequest.getParameter("otp") != null) {
				userUtterance = servletRequest.getParameter("otp");
			}
			if (servletRequest.getParameter("nessaQuestionList") != null) {
				nessaQuestion = questionList;
				userUtterance = answerList;
			}
			//System.out.println("#######################3  ipAddress"+ipAddress);
			final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
			BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(httpRequest);
		
			final HttpServletResponse response = (HttpServletResponse) servletResponse;
			final ByteArrayPrintWriter pw = new ByteArrayPrintWriter();
			HttpServletResponse wrappedResp = new HttpServletResponseWrapper(response) {
				public PrintWriter getWriter() {
					return pw.getWriter();
				}

				public ServletOutputStream getOutputStream() {
					return pw.getStream();
				}
			};
			chain.doFilter(bufferedRequest, wrappedResp);
			byte[] bytes = pw.toByteArray();
			response.getOutputStream().write(bytes);
			ServiceLogModel serviceLogModel = null;
			//call method to save servicelog
			
			//change by vaibhav
			//flag = adminService.addgonogodetails(gonogoDtoList);
			System.out.println("#######################################hello demo#############################");
			
			
			serviceLogModel = adminService.saveServiceLog(ipAddress, taskName, applicationName, nessaQuestion,
					userUtterance, serviceName, bytes);
			if (servletRequest.getParameter("nessaQuestionList") != null) {
				nessaQuestion = questionList;
				userUtterance = answerList;
				//call method to store intermeddiatelog
			/*	boolean saveServiceLogIntermeddiate = adminService.saveServiceLogIntermeddiate(taskName, nessaQuestion,
						userUtterance, nessaQuestion, userUtterance, serviceLogModel);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}


