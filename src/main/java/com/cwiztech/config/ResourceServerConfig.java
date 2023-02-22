//package com.cwiztech.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//	@Bean
//	protected ResourceServerConfiguration adminResources() {
//
//		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
//			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
//				super.setConfigurers(configurers);
//			}
//		};
//		resource.setConfigurers(
//				Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
//					@Override
//					public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//						resources.resourceId(OAuth2Config.RESOURCE_ID1);
//					}
//
//					@Override
//					public void configure(HttpSecurity http) throws Exception {
//						http.requestMatchers().antMatchers("/lettertemplate", "/lettertemplate/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//					}
//				}));
//
//		resource.setOrder(3);
//		return resource;
//	}
//
//	@Bean
//	protected ResourceServerConfiguration VleResources() {
//
//		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
//			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
//				super.setConfigurers(configurers);
//			}
//		};
//		resource.setConfigurers(
//				Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
//					@Override
//					public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//						resources.resourceId(OAuth2Config.RESOURCE_ID4);
//					}
//
//					@Override
//					public void configure(HttpSecurity http) throws Exception {
//						http.requestMatchers().antMatchers("/vlestudent", "/vlestudent/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//
//						http.requestMatchers().antMatchers("/moduleassessmentcriteria", "/moduleassessmentcriteria/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						http.requestMatchers().antMatchers("/modulelearningoutcome", "/modulelearningoutcome/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						http.requestMatchers().antMatchers("/studentmodule", "/studentmodule/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						http.requestMatchers().antMatchers("/logout", "/logout/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//					}
//				}));
//
//		resource.setOrder(2);
//		return resource;
//	}
//
//	@Bean
//	protected ResourceServerConfiguration userResources() {
//		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
//			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
//				super.setConfigurers(configurers);
//			}
//		};
//		resource.setConfigurers(
//				Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
//					@Override
//					public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//						resources.resourceId(OAuth2Config.RESOURCE_ID2);
//					}
//
//					@Override
//					public void configure(HttpSecurity http) throws Exception {
//						/*
//						 * Academic Management
//						 */
//
//						http.requestMatchers().antMatchers("/academicscalendar", "/academicscalendar/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/academicsyear", "/academicsyear/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/building", "/building/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/university", "/university/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/campus", "/campus/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/college", "/college/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/department", "/department/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/intake", "/intake/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/intakecourse", "/intakecourse/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/qualification", "/qualification/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/room", "/room/**").and().authorizeRequests().anyRequest()
//								.access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/sample", "/sample/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/semester", "/semester/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/semestertimeslot", "/semestertimeslot/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * admission management
//						 */
//
//						http.requestMatchers().antMatchers("/admission", "/admission/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/admissionloginuser", "/admissionloginuser/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentapplication", "/studentapplication/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/studentapplicationsection", "/studentapplicationsection/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentassessmentfinal", "/studentassessmentfinal/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentassessmentinitial", "/studentassessmentinitial/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentinterview", "/studentinterview/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Postal Address
//						 */
//
//						http.requestMatchers().antMatchers("/findaddress", "/findaddress/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Course Management
//						 */
//
//						http.requestMatchers().antMatchers("/course", "/course/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/coursedetail", "/coursedetail/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/coursemodule", "/coursemodule/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/coursesubject", "/coursesubject/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/moduleassessmentcriteria",
//						// "/moduleassessmentcriteria/**")
//						// .and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/module", "/module/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/modulelearningoutcome",
//						// "/modulelearningoutcome/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Department Management
//						 */
//
//						http.requestMatchers()
//								.antMatchers("/academicssemesterintakemodulesection",
//										"/academicssemesterintakemodulesection/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/academicsemesterintakemodule", "/academicsemesterintakemodule/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * HesaManagement
//						 */
//
//						http.requestMatchers().antMatchers("/hesacourse", "/hesacourse/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesaintakecourse", "/hesaintakecourse/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesarecordtype", "/hesarecordtype/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesastudent", "/hesastudent/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesastudententryprofile", "/hesastudententryprofile/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesastudentequality", "/hesastudentequality/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/hesastudentinstance", "/hesastudentinstance/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/hesastudentinstanceperiod", "/hesastudentinstanceperiod/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/hesastudentinstanceperiodqualificationawarded",
//										"/hesastudentinstanceperiodqualificationawarded/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/hesastudentqualificationsonentry",
//										"/hesastudentqualificationsonentry/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * HR Management
//						 */
//
//						http.requestMatchers()
//								.antMatchers("/employeecommunicationletter", "/employeecommunicationletter/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeecommunicationsms", "/employeecommunicationsms/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeecontact", "/employeecontact/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employee", "/employee/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeedocument", "/employeedocument/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/employeeeducationinstitute", "/employeeeducationinstitute/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/employeeeducationqualification", "/employeeeducationqualification/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/employeeeducationqualificationmodule",
//										"/employeeeducationqualificationmodule/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeeemployment", "/employeeemployment/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeeequality", "/employeeequality/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeemembership", "/employeemembership/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeeotherinfo", "/employeeotherinfo/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/employeeprofessionalbodies", "/employeeprofessionalbodies/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeequalification", "/employeequalification/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/employeereference", "/employeereference/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/semesteremployeemodule", "/semesteremployeemodule/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Library Management
//						 */
//
//						http.requestMatchers().antMatchers("/librarybiblio", "/librarybiblio/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybiblioframework", "/librarybiblioframework/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybiblioimages", "/librarybiblioimages/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybiblioitems", "/librarybiblioitems/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybibliometadata", "/librarybibliometadata/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/libraryborrower", "/libraryborrower/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybranchcategory", "/librarybranchcategory/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybranches", "/librarybranches/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybranchrelations", "/librarybranchrelations/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarybranchtransfers", "/librarybranchtransfers/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/libraryissues", "/libraryissues/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/libraryitems", "/libraryitems/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/librarypatroncategories", "/librarypatroncategories/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Login
//						 */
//
//						http.requestMatchers().antMatchers("/login", "/login/**").and().authorizeRequests().anyRequest()
//								.access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginprivilegecategory", "/loginprivilegecategory/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginprivilege", "/loginprivilege/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginrole", "/loginrole/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginroleprivilege", "/loginroleprivilege/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginuser", "/loginuser/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginuserletterletter", "/loginuserletterletter/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginuserprivilege", "/loginuserprivilege/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginuserrole", "/loginuserrole/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						http.requestMatchers().antMatchers("/oauth/token", "/oauth/token").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//
//						// http.requestMatchers().antMatchers("/logout",
//						// "/logout/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Mail Box Management
//						 */
//
//						http.requestMatchers().antMatchers("/loginusermaillabel", "/loginusermaillabel/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/loginusermailmessageattachment", "/loginusermailmessageattachment/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/loginusermailmessage", "/loginusermailmessage/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/loginusermailmessagelable", "/loginusermailmessagelable/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/loginusermailmessagerecipient", "/loginusermailmessagerecipient/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * Student Management
//						 */
//
//						http.requestMatchers()
//								.antMatchers("/studentcommunicationletter", "/studentcommunicationletter/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentcommunicationsms", "/studentcommunicationsms/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentcontact", "/studentcontact/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentcontactprefer", "/studentcontactprefer/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/student", "/student/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentdocument", "/studentdocument/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/studenteducationinstitute", "/studenteducationinstitute/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/studenteducationqualification", "/studenteducationqualification/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/studenteducationqualificationmodule",
//										"/studenteducationqualificationmodule/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentemployment", "/studentemployment/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentenglishdetail", "/studentenglishdetail/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentequality", "/studentequality/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers()
//								.antMatchers("/studentinstancechangeofcircumstance",
//										"/studentinstancechangeofcircumstance/**")
//								.and().authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentinstance", "/studentinstance/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/studentmodule",
//						// "/studentmodule/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentotherinfo", "/studentotherinfo/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentqualification", "/studentqualification/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						http.requestMatchers().antMatchers("/studentreference", "/studentreference/**").and()
//								.authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//
//						/*
//						 * System Settings
//						 */
//						// http.requestMatchers().antMatchers("/v2/api-docs",
//						// "/v2/api-docs/**").and().authorizeRequests()
//						// .anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/swagger-ui.html",
//						// "/swagger-ui.html/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/swagger-resources",
//						// "/swagger-resources/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/lettertemplate",
//						// "/lettertemplate/**").and()
//						// .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')");
//						// http.requestMatchers().antMatchers("/lookup",
//						// "/lookup/**").and().authorizeRequests()
//						// .anyRequest().access("#oauth2.hasScope('write')");
//					}
//				}));
//		resource.setOrder(4);
//		return resource;
//	}
//
//	@Bean
//	protected ResourceServerConfiguration RigesterUser() {
//		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
//			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
//				super.setConfigurers(configurers);
//			}
//		};
//		resource.setConfigurers(
//				Collections.<ResourceServerConfigurer>singletonList(new ResourceServerConfigurerAdapter() {
//					@Override
//					public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//						resources.resourceId(OAuth2Config.RESOURCE_ID3);
//					}
//
//					@Override
//					public void configure(HttpSecurity http) throws Exception {
//						http.requestMatchers().antMatchers("/lookup", "/lookup/**").and().authorizeRequests()
//								.anyRequest().access("#oauth2.hasScope('write')");
//					}
//				}));
//		resource.setOrder(5);
//		return resource;
//	}
//}
