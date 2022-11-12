/*
 * Copyright (C) 2015-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.stream.javadsl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.ClassRule;
import org.junit.Test;

import org.apache.pekko.stream.Attributes;
import org.apache.pekko.stream.StreamTest;
import org.apache.pekko.testkit.AkkaJUnitActorSystemResource;
import org.apache.pekko.testkit.AkkaSpec;

public class AttributesTest extends StreamTest {

  public AttributesTest() {
    super(actorSystemResource);
  }

  @ClassRule
  public static AkkaJUnitActorSystemResource actorSystemResource =
      new AkkaJUnitActorSystemResource("AttributesTest", AkkaSpec.testConf());

  final Attributes attributes =
      Attributes.name("a").and(Attributes.name("b")).and(Attributes.inputBuffer(1, 2));

  @Test
  public void mustGetAttributesByClass() {
    assertEquals(
        Arrays.asList(new Attributes.Name("b"), new Attributes.Name("a")),
        attributes.getAttributeList(Attributes.Name.class));
    assertEquals(
        Collections.singletonList(new Attributes.InputBuffer(1, 2)),
        attributes.getAttributeList(Attributes.InputBuffer.class));
  }

  @Test
  public void mustGetAttributeByClass() {
    assertEquals(
        new Attributes.Name("b"),
        attributes.getAttribute(Attributes.Name.class, new Attributes.Name("default")));
  }

  @Test
  public void mustGetMissingAttributeByClass() {
    assertEquals(Optional.empty(), attributes.getAttribute(Attributes.LogLevels.class));
  }

  @Test
  public void mustGetPossiblyMissingAttributeByClass() {
    assertEquals(
        Optional.of(new Attributes.Name("b")), attributes.getAttribute(Attributes.Name.class));
  }

  @Deprecated
  @Test
  public void mustGetPossiblyMissingFirstAttributeByClass() {
    assertEquals(
        Optional.of(new Attributes.Name("a")), attributes.getFirstAttribute(Attributes.Name.class));
  }

  @Deprecated
  @Test
  public void mustGetMissingFirstAttributeByClass() {
    assertEquals(Optional.empty(), attributes.getFirstAttribute(Attributes.LogLevels.class));
  }
}