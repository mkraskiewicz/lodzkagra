import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { LodzkagraSharedModule } from 'app/shared/shared.module';
import { LodzkagraCoreModule } from 'app/core/core.module';
import { LodzkagraAppRoutingModule } from './app-routing.module';
import { LodzkagraHomeModule } from './home/home.module';
import { LodzkagraEntityModule } from './entities/entity.module';
import { LodzkagraAppZadaniaModule } from './zadania/zadania.module';
import { Injector, NO_ERRORS_SCHEMA } from '@angular/core';
import { LodzkagraAppRankingModule } from './ranking/ranking.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    LodzkagraSharedModule,
    LodzkagraCoreModule,
    LodzkagraHomeModule,
    LodzkagraAppZadaniaModule,
    LodzkagraAppRankingModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    LodzkagraEntityModule,
    LodzkagraAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
  schemas: [NO_ERRORS_SCHEMA],
})
export class LodzkagraAppModule {}
