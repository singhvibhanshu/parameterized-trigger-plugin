package hudson.plugins.parameterizedtrigger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
public class BuildTriggerConfigTest {

    @Test
    public void testOnJobRenamedMultipleProjects(JenkinsRule j) throws Exception {
        j.createFreeStyleProject("project_2");
        j.createFreeStyleProject("project_3");

        BuildTriggerConfig config =
                new BuildTriggerConfig("project_2, project_3", ResultCondition.SUCCESS, false, null);
        boolean changed = config.onJobRenamed(j.jenkins, "project_3", "project_5");

        assertTrue(changed, "The config should report that it was changed");
        assertEquals("project_2,project_5", config.getProjects());
    }
}
