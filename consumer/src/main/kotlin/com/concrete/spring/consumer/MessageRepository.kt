package com.concrete.spring.consumer

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : ReactiveCrudRepository<Message, String>
