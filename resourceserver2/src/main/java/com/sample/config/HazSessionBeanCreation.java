package com.sample.config;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


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
		Config config= new XmlConfigBuilder(hazelcastFolder.concat(profile).concat("_HazelcastDistributedSession.xml")).build();
		return  Hazelcast.newHazelcastInstance(config); 
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("Hazelcast Configuration with ".concat(hazelcastFolder.concat(profile).concat("_HazelcastDistributedSession.xml")).concat(" Not Found"));
		}
		

	}
	
	
}
