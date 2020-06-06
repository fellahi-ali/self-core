package com.selfxdsd.core.contracts;

import com.selfxdsd.api.Contract;
import com.selfxdsd.api.Contributor;
import com.selfxdsd.api.Invoices;
import com.selfxdsd.api.Project;
import com.selfxdsd.api.storage.Storage;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

/**
 * Unit tests for {@link StoredContract}.
 *
 * @author hpetrila
 * @version $Id$
 * @since 0.0.1
 */
public final class StoredContractTestCase {

    /**
     * StoredContract returns its id.
     */
    @Test
    public void returnsProject() {
        final Project project = Mockito.mock(Project.class);
        final Contract contract = new StoredContract(
            project,
            Mockito.mock(Contributor.class),
            BigDecimal.ONE, "DEV",
            Mockito.mock(Storage.class)
        );
        MatcherAssert.assertThat(contract.project(), Matchers.is(project));
    }

    /**
     * StoredContract returns its contributor.
     */
    @Test
    public void returnsContributor() {
        final Contributor contributor = Mockito.mock(Contributor.class);
        final Contract contract = new StoredContract(
            Mockito.mock(Project.class),
            contributor,
            BigDecimal.ONE, "DEV",
            Mockito.mock(Storage.class)
        );
        MatcherAssert.assertThat(
            contract.contributor(),
            Matchers.is(contributor));
    }

    /**
     * StoredContract returns its hourly rate.
     */
    @Test
    public void returnsHourlyRate() {
        final Contract contract = new StoredContract(
            Mockito.mock(Project.class),
            Mockito.mock(Contributor.class),
            BigDecimal.ONE, "DEV",
            Mockito.mock(Storage.class)
        );
        MatcherAssert.assertThat(
            contract.hourlyRate(),
            Matchers.equalTo(BigDecimal.ONE));
    }

    /**
     * StoredContract returns its role.
     */
    @Test
    public void returnsRole() {
        final Contract contract = new StoredContract(
            Mockito.mock(Project.class),
            Mockito.mock(Contributor.class),
            BigDecimal.ONE, "DEV",
            Mockito.mock(Storage.class));
        MatcherAssert.assertThat(contract.role(), Matchers.equalTo("DEV"));
    }

    /**
     * Returns contracts invoices.
     */
    @Test
    public void returnsInvoices(){
        final Storage storage = Mockito.mock(Storage.class);
        final Contract contract = new StoredContract(
            Mockito.mock(Project.class),
            Mockito.mock(Contributor.class),
            BigDecimal.ONE, "DEV",
            storage);
        final Invoices all = Mockito.mock(Invoices.class);
        final Invoices invoices = Mockito.mock(Invoices.class);

        Mockito.when(all.ofContract(Mockito.any(Contract.Id.class)))
            .thenReturn(invoices);
        Mockito.when(storage.invoices()).thenReturn(all);

        MatcherAssert.assertThat(
            contract.invoices(),
            Matchers.is(invoices)
        );
    }
}
