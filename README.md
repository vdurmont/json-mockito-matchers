json-mockito-matchers
================================

Mockito matchers that will help you to check the usage of [JSONObject](http://www.json.org/javadoc/org/json/JSONObject.html)s and [JSONArray](http://www.json.org/javadoc/org/json/JSONArray.html)s.

# Dependencies

* `org.mockito > mockito-core` version 1.9.5
* `org.json > json` version 20140107

# Usage

Soon available on ~Maven Central Repository~.

Add the dependency in your `pom.xml`.

	<dependency>
		<groupId>com.github.vdurmont</groupId>
		<artifactId>json-mockito-matcher</artifactId>
		<version>INSERT_THE_CURRENT_VERSION_HERE</version>
		<scope>test</scope>
	</dependency>

Write your tests:
	
	import org.json.JSONObject;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.junit.runners.JUnit4;
	
	import static com.github.vdurmont.JSONObjectMatcher.jsonEq;
	import static org.mockito.Mockito.mock;
	import static org.mockito.Mockito.verify;

	@RunWith(JUnit4.class)
	public class MyTestClass {
		private static final MyMockedClass myMock = mock(MyMockedClass.class);

		@Test
		public void myTest() {
			// GIVEN
			MyService myService = new MyService(myMock);

			JSONObject expected = new JSONObject();
			expected.put("data", "data");

			// WHEN
			this.myService.someMethod();

			// THEN
			verify(myMock).myMethod(jsonEq(expected));
		}
	}

It also works with a JSONArray :)