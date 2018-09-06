package uk.nhs.careconnect.ri.gateway.http;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;

import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.server.FifoMemoryPagingProvider;
import ca.uhn.fhir.rest.server.HardcodedServerAddressStrategy;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.util.VersionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import uk.nhs.careconnect.ri.gatewaylib.provider.*;
import uk.nhs.careconnect.ri.lib.ServerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.TimeZone;


public class HAPIRestfulConfig extends RestfulServer {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HAPIRestfulConfig.class);

	@Value("http://127.0.0.1/STU3")
	private String serverBase;

	@Value("${fhir.resource.serverName}")
	private String serverName;

	@Value("${fhir.resource.serverVersion}")
	private String serverVersion;

	private ApplicationContext applicationContext;

	HAPIRestfulConfig(ApplicationContext context)  {
		this.applicationContext = context;
	}

    @Override
	public void addHeadersToResponse(HttpServletResponse theHttpResponse) {
		theHttpResponse.addHeader("X-Powered-By", "HAPI FHIR " + VersionUtil.getVersion() + " RESTful Server (NHS Care Connect STU3)");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initialize() throws ServletException {
		super.initialize();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));


		FhirVersionEnum fhirVersion = FhirVersionEnum.DSTU3;
		setFhirContext(new FhirContext(fhirVersion));

		/*
		// Get the spring context from the web container (it's declared in web.xml)
		//applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		//Config cfg = applicationContext.getBean(uk.nhs.careconnect.ri.gateway.http.Config.class);
  
		String serverBase = cfg.getServerBase();
		String serverName = cfg.getServerName();
		String serverVersion = cfg.getServerVersion();
*/
		log.info("serverBase: " + serverBase);
		log.info("serverName: " + serverName);
		log.info("serverVersion: " + serverVersion);

        if (serverBase != null && !serverBase.isEmpty()) {
            setServerAddressStrategy(new HardcodedServerAddressStrategy(serverBase));
        }


		setResourceProviders(Arrays.asList(

				// Care Connect API providers START

				 applicationContext.getBean(PatientResourceProvider.class)
               	,applicationContext.getBean(OrganisationResourceProvider.class)
                ,applicationContext.getBean(PractitionerResourceProvider.class)
                ,applicationContext.getBean(LocationResourceProvider.class)
               	,applicationContext.getBean(PractitionerRoleResourceProvider.class)
      			,applicationContext.getBean(ObservationResourceProvider.class) // Sprint 4 addition KGM
				,applicationContext.getBean(EncounterResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(ConditionResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(ProcedureResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(AllergyIntoleranceResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(MedicationRequestResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(MedicationStatementResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(ImmunizationResourceProvider.class) // R3 addition KGM
				,applicationContext.getBean(DocumentReferenceResourceProvider.class) // Unstructured
				,applicationContext.getBean(BinaryResourceProvider.class) // Unstructured
				,applicationContext.getBean(MedicationResourceProvider.class)

				// Care Connect API providers END

				// Support for NHS Digital National Projects  START

				// NOT FOR LIVE - Use ENVIRONEMNT VARIABLE

				,applicationContext.getBean(QuestionnaireResourceProvider.class)
				,applicationContext.getBean(QuestionnaireResponseResourceProvider.class)
				,applicationContext.getBean(ListResourceProvider.class)
				,applicationContext.getBean(RelatedPersonResourceProvider.class)
				,applicationContext.getBean(CarePlanResourceProvider.class)
				,applicationContext.getBean(HealthcareServiceResourceProvider.class)
				,applicationContext.getBean(ReferralRequestResourceProvider.class)
				,applicationContext.getBean(CareTeamResourceProvider.class)
				,applicationContext.getBean(MedicationDispenseResourceProvider.class)
				,applicationContext.getBean(GoalResourceProvider.class)
				,applicationContext.getBean(RiskAssessmentResourceProvider.class)
				,applicationContext.getBean(ClinicalImpressionResourceProvider.class)
				,applicationContext.getBean(ConsentResourceProvider.class)
				,applicationContext.getBean(BundleResourceProvider.class) // Supports uploading resources
				,applicationContext.getBean(EpisodeOfCareResourceProvider.class) // TO DO Remove me for live KGM

				// Support for NHS Digital Natinal Projects  END

				// NOT FOR LIVE


		));

        // Replace built in conformance provider (CapabilityStatement)
        setServerConformanceProvider(new CareConnectConformanceProvider( ));

        setServerName(serverName);
        setServerVersion(serverVersion);


		// This is the format for each line. A number of substitution variables may
		// be used here. See the JavaDoc for LoggingInterceptor for information on
		// what is available.

		ServerInterceptor gatewayInterceptor = new ServerInterceptor(log);
		registerInterceptor(gatewayInterceptor);
		//gatewayInterceptor.setLoggerName("ccri.FHIRGateway");
		//gatewayInterceptor.setLogger(ourLog);

		// This paging provider is not robust KGM 24/11/2017

		// Mocking of a database Paging Provider is in server

		FifoMemoryPagingProvider pp = new FifoMemoryPagingProvider(10);
		pp.setDefaultPageSize(10);
		pp.setMaximumPageSize(100);
		setPagingProvider(pp);

		setDefaultPrettyPrint(true);
		setDefaultResponseEncoding(EncodingEnum.JSON);

		FhirContext ctx = getFhirContext();
		// Remove as believe due to issues on docker ctx.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
	}
}
