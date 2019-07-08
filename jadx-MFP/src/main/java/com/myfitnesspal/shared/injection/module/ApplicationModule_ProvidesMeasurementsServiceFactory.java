package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.MeasurementTypesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMeasurementsServiceFactory implements Factory<MeasurementsService> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;
    private final Provider<DeletedItemsTable> deletedItemsTableProvider;
    private final Provider<ImageAssociationService> imageAssociationServiceProvider;
    private final Provider<MeasurementTypesTable> measurementTypesTableProvider;
    private final Provider<MeasurementsTable> measurementsTableProvider;
    private final Provider<ExternalUserService> mfpFitUserServiceProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesMeasurementsServiceFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<ExternalUserService> provider2, Provider<ImageAssociationService> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<MeasurementsTable> provider5, Provider<MeasurementTypesTable> provider6, Provider<DeletedItemsTable> provider7) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.mfpFitUserServiceProvider = provider2;
        this.imageAssociationServiceProvider = provider3;
        this.databaseProvider = provider4;
        this.measurementsTableProvider = provider5;
        this.measurementTypesTableProvider = provider6;
        this.deletedItemsTableProvider = provider7;
    }

    public MeasurementsService get() {
        return provideInstance(this.module, this.sessionProvider, this.mfpFitUserServiceProvider, this.imageAssociationServiceProvider, this.databaseProvider, this.measurementsTableProvider, this.measurementTypesTableProvider, this.deletedItemsTableProvider);
    }

    public static MeasurementsService provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<ExternalUserService> provider2, Provider<ImageAssociationService> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<MeasurementsTable> provider5, Provider<MeasurementTypesTable> provider6, Provider<DeletedItemsTable> provider7) {
        return proxyProvidesMeasurementsService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), (SQLiteDatabaseWrapper) provider4.get(), (MeasurementsTable) provider5.get(), (MeasurementTypesTable) provider6.get(), (DeletedItemsTable) provider7.get());
    }

    public static ApplicationModule_ProvidesMeasurementsServiceFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<ExternalUserService> provider2, Provider<ImageAssociationService> provider3, Provider<SQLiteDatabaseWrapper> provider4, Provider<MeasurementsTable> provider5, Provider<MeasurementTypesTable> provider6, Provider<DeletedItemsTable> provider7) {
        ApplicationModule_ProvidesMeasurementsServiceFactory applicationModule_ProvidesMeasurementsServiceFactory = new ApplicationModule_ProvidesMeasurementsServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return applicationModule_ProvidesMeasurementsServiceFactory;
    }

    public static MeasurementsService proxyProvidesMeasurementsService(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<ExternalUserService> lazy2, Lazy<ImageAssociationService> lazy3, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, MeasurementsTable measurementsTable, MeasurementTypesTable measurementTypesTable, DeletedItemsTable deletedItemsTable) {
        return (MeasurementsService) Preconditions.checkNotNull(applicationModule.providesMeasurementsService(lazy, lazy2, lazy3, sQLiteDatabaseWrapper, measurementsTable, measurementTypesTable, deletedItemsTable), "Cannot return null from a non-@Nullable @Provides method");
    }
}
