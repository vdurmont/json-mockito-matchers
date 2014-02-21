package com.github.vdurmont;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class JSONObjectMatcherTest {
	@Test
	public void matches_null_with_null_expected_is_true() {
		// GIVEN
		JSONObject expected = null;

		// WHEN
		JSONObject actual = null;

		// THEN
		assertTrue(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_null_with_not_null_expected_is_false() {
		// GIVEN
		JSONObject expected = new JSONObject();

		// WHEN
		JSONObject actual = null;

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_not_null_with_null_expected_is_false() {
		// GIVEN
		JSONObject expected = null;

		// WHEN
		JSONObject actual = new JSONObject();

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_same_object_is_true() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");

		// THEN
		assertTrue(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_same_object_with_nested_JSONObject_is_true() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		JSONObject nestedExp = new JSONObject();
		nestedExp.put("data2", "some other data");
		expected.put("nested", nestedExp);

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		JSONObject nestedAct = new JSONObject();
		nestedAct.put("data2", "some other data");
		actual.put("nested", nestedAct);

		// THEN
		assertTrue(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_object_with_different_nested_JSONObject_is_false() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		JSONObject nestedExp = new JSONObject();
		nestedExp.put("data2", "some other data");
		expected.put("nested", nestedExp);

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		JSONObject nestedAct = new JSONObject();
		nestedAct.put("data2", "some different data");
		actual.put("nested", nestedAct);

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_object_with_missing_key_is_false() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		expected.put("data2", "some data 2");

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_object_with_additional_key_is_false() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		actual.put("data2", "some data 2");

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_same_object_with_nested_JSONArray_is_true() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		JSONArray arr1 = new JSONArray();
		arr1.put("one");
		arr1.put("two");
		expected.put("arr", arr1);

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		JSONArray arr2 = new JSONArray();
		arr2.put("one");
		arr2.put("two");
		actual.put("arr", arr2);

		// THEN
		assertTrue(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_object_with_different_nested_JSONArray_is_false() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		JSONArray arr1 = new JSONArray();
		arr1.put("one");
		arr1.put("two");
		expected.put("arr", arr1);

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		JSONArray arr2 = new JSONArray();
		arr2.put("one");
		arr2.put("two");
		arr2.put("three");
		actual.put("arr", arr2);

		// THEN
		assertFalse(new JSONObjectMatcher(expected).matches(actual));
	}

	@Test
	public void matches_same_object_with_a_null_value_is_true() {
		// GIVEN
		JSONObject expected = new JSONObject();
		expected.put("data", "some data");
		expected.put("data2", JSONObject.NULL);

		// WHEN
		JSONObject actual = new JSONObject();
		actual.put("data", "some data");
		actual.put("data2", JSONObject.NULL);

		// THEN
		assertTrue(new JSONObjectMatcher(expected).matches(actual));
	}
}
