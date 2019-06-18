package com.sample.config;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
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
import com.sample.web.initializer.ObjectStreamSerializer;


@Profile("!dev")
@EnableHazelcastHttpSession
public class HazSessionConfig {

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
		
		config.setInstanceName("hzSession");
		SerializerConfig serializer = new SerializerConfig()
				.setImplementation(new ObjectStreamSerializer())
				.setTypeClass(Object.class);

		config.getSerializationConfig()
				.addSerializerConfig(serializer);
		
		config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME) 
		.addMapAttributeConfig(attributeConfig)
		.addMapIndexConfig(new MapIndexConfig(
				HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
		cacheCreation();
		return  Hazelcast.newHazelcastInstance(config); 
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("Hazelcast Configuration with ".concat(hazelcastFolder.concat(profile).concat("_HazelcastDistributedSession.xml")).concat(" Not Found"));
		}
		

	}
	
	 
		
	public void cacheCreation() {
		Config config;
		try {
			config = new XmlConfigBuilder(hazelcastFolder.concat(profile).concat("_HazelcastGlobalCache.xml")).build();
			config.setInstanceName("hzCache");
			HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}

/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*package com.sample.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.sample.web.initializer.ObjectStreamSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.util.SocketUtils;

// tag::class[]
@EnableHazelcastHttpSession(maxInactiveIntervalInSeconds = 300)
@Configuration
public class HazSessionConfig {

	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance hazelcastInstance() {
		Config config = new Config();

		int port = SocketUtils.findAvailableTcpPort();

		config.getGroupConfig().setName("qa_HazelcastDistributedSession");
		 
//		config.getNetworkConfig()
//				.setPort(port)
//				.getJoin().getMulticastConfig().setEnabled(false);
		
		config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true).addMember("127.0.0.1:"+port);
        config.setInstanceName("qa_HazelcastDistributedSession");
        config.getNetworkConfig().setPort(port);

		System.out.println("Hazelcast port #: " + port);

		SerializerConfig serializer = new SerializerConfig()
				.setImplementation(new ObjectStreamSerializer())
				.setTypeClass(Object.class);

		config.getSerializationConfig()
				.addSerializerConfig(serializer);

		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME)
				.addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(
						HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		return Hazelcast.newHazelcastInstance(config);
	}

}
// end::class[]
*/