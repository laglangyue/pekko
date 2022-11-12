/*
 * Copyright (C) 2018-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.remote

import com.typesafe.config.ConfigFactory

import org.apache.pekko.actor.ActorMailboxSpec

class RemoteActorMailboxSpec
    extends ActorMailboxSpec(
      ConfigFactory.parseString("""akka.actor.provider = remote""").withFallback(ActorMailboxSpec.mailboxConf)) {}