package co.gateway.zuul.config;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import co.gateway.zuul.initializer.ObjectStreamSerializer;


@Profile("!dev")
@Configuration
@EnableHazelcastHttpSession
public class HazSessionBeanCreation {

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${hazelcast.config.folder}")
	private String hazelcastFolder;
	
	@Profile("!dev")
	@Bean
	public HazelcastInstance hazelcastSession() throws FileNotFoundException {
		try {
			MapAttributeConfig attributeConfig = new MapAttributeConfig()
					.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
					.setExtractor(PrincipalNameExtractor.class.getName());
			
		Config config= new XmlConfigBuilder(hazelcastFolder.concat(profile).concat("_HazelcastDistributedSession.xml")).build();
		SerializerConfig serializer = new SerializerConfig()
				.setImplementation(new ObjectStreamSerializer())
				.setTypeClass(Object.class);

		config.getSerializationConfig()
				.addSerializerConfig(serializer);
		config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME) 
		.addMapAttributeConfig(attributeConfig)
		.addMapIndexConfig(new MapIndexConfig(
				HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
		return  Hazelcast.newHazelcastInstance(config); 
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("Hazelcast Configuration with ".concat(hazelcastFolder.concat(profile).concat("_HazelcastDistributedSession.xml")).concat(" Not Found"));
		}
		

	}
}
