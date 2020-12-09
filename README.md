- Running the initial 'select p from Player p where p.name='Bill'' results in the creation of a EnhancementAsProxyLazinessInterceptor for the Team
- The second select loads the players and the teams, they are processed as hydrated objects in org.hibernate.loader.Loader.initializeEntitiesAndCollections(java.util.List, java.lang.Object, org.hibernate.engine.spi.SharedSessionContractImplementor, boolean, java.util.List<org.hibernate.loader.spi.AfterLoadAction>)
- Because Team is fetched eagerly EnhancementAsProxyLazinessInterceptor org.hibernate.loader.Loader.instanceAlreadyLoaded will load it properly when the first Player is loaded, and will also load Bob through the Best Player reference
- This mutates the loadedState in the EntityEntry for Bob, so when it is loaded its EntityEntry no longer has the id primitive in the loaded state, but rather the actual Person object, so when it gets to processing Bob it fails

Basically this bug occurs when:
- A lazy entity E has been created and has a EnhancementAsProxyLazinessInterceptor installed
- A query is executed that results in loading an entity that references E, as well as E itself, with the referencing entity being processed first


Note that if the entities don't implement Serializable the error is slightly different