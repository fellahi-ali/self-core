package com.selfxdsd.core.tasks;

import com.selfxdsd.api.*;
import com.selfxdsd.api.storage.Storage;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Unit tests for {@link ContributorTasks}.
 *
 * @author criske
 * @version $Id$
 * @since 0.0.1
 */
public final class ContributorTasksTestCase {

    /**
     * ContributorTasks should be iterable.
     */
    @Test
    public void canBeIterated() {
        final Tasks tasks = new ContributorTasks(
            "foo",
            "github", List.of(
            Mockito.mock(Task.class),
            Mockito.mock(Task.class),
            Mockito.mock(Task.class)
        ),
            Mockito.mock(Storage.class)
        );
        MatcherAssert.assertThat(tasks, Matchers.iterableWithSize(3));
    }

    /**
     * Should register a new task associated with an issue.
     * Right now is throwing UOE.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void registerTask() {
        final Issue issue = this.mockIssue(
            "123",
            "john/other",
            "github",
            Contract.Roles.DEV
        );
        final Tasks tasks = new ContributorTasks(
            "foo", "github", List.of(),
            Mockito.mock(Storage.class)
        );
        tasks.register(issue);
    }


    /**
     * Should return new ContributorTask when searching for same
     * contributor + provider key.
     */
    @Test
    public void returnSameTasksForSameUserNameProvider(){
        final Tasks tasks = new ContributorTasks(
            "foo", "gitlab", List.of(),
            Mockito.mock(Storage.class)
        );
        MatcherAssert.assertThat(
            tasks.ofContributor("foo", "gitlab"),
            Matchers.equalTo(tasks)
        );
    }

    /**
     * Should return new ContributorTasks when searching for different
     * contributor + provider key.
     */
    @Test
    public void returnNewTasksForDiffUserNameProvider(){
        final Storage storage = Mockito.mock(Storage.class);
        final Task registered = Mockito.mock(Task.class);
        final Contributor contributor = Mockito.mock(Contributor.class);
        Mockito.when(contributor.username()).thenReturn("mihai");
        Mockito.when(contributor.provider()).thenReturn("github");
        Mockito.when(registered.assignee()).thenReturn(contributor);
        final Tasks all = Mockito.mock(Tasks.class);
        Mockito.when(all.spliterator())
            .thenReturn(List.of(registered).spliterator());
        Mockito.when(storage.tasks()).thenReturn(all);

        final Tasks tasks = new ContributorTasks(
            "foo", "gitlab", List.of(),
            storage
        );

        Tasks tasksOfMihai = tasks.ofContributor("mihai", "github");
        MatcherAssert.assertThat(
            tasksOfMihai,
            Matchers.not(Matchers.equalTo(tasks))
        );
        MatcherAssert.assertThat(
            tasksOfMihai,
            Matchers.iterableWithSize(1)
        );
    }


    /**
     * Should return tasks of a project.
     */
    @Test
    public void tasksOfProject(){
        final Storage storage = Mockito.mock(Storage.class);

        final Project projectOne = Mockito.mock(Project.class);
        Mockito.when(projectOne.repoFullName()).thenReturn("mihai");
        Mockito.when(projectOne.provider()).thenReturn(Provider.Names.GITHUB);
        final Task taskOne = Mockito.mock(Task.class);
        Mockito.when(taskOne.project()).thenReturn(projectOne);

        final Project projectTwo = Mockito.mock(Project.class);
        Mockito.when(projectTwo.repoFullName()).thenReturn("mihai/other");
        Mockito.when(projectTwo.provider()).thenReturn(Provider.Names.GITLAB);
        final Task taskTwo = Mockito.mock(Task.class);
        Mockito.when(taskTwo.project()).thenReturn(projectTwo);

        final Tasks tasks = new ContributorTasks(
            "mihai", Provider.Names.GITHUB,
            List.of(
                taskOne,
                taskTwo
            ),
            storage
        );

        MatcherAssert.assertThat(
            tasks.ofProject("mihai", Provider.Names.GITHUB),
            Matchers.iterableWithSize(1)
        );
    }

    /**
     * Throws UnsupportedOperationException.
     * Contributor should not have unassigned tasks.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void throwsWhenReturnsUnassignedTasks(){
        new ContributorTasks(
            "mihai", Provider.Names.GITHUB,
            List.of(), Mockito.mock(Storage.class))
            .unassigned();
    }


    /**
     * Mock an Issue for test.
     *
     * @param issueId ID.
     * @param repoFullName Repo fullname.
     * @param provider Provider.
     * @param role Role.
     * @return Issue.
     */
    private Issue mockIssue(
        final String issueId, final String repoFullName,
        final String provider, final String role) {
        Issue issue = Mockito.mock(Issue.class);
        Mockito.when(issue.issueId()).thenReturn(issueId);
        Mockito.when(issue.repoFullName()).thenReturn(repoFullName);
        Mockito.when(issue.provider()).thenReturn(provider);
        Mockito.when(issue.role()).thenReturn(role);
        return issue;
    }
}
