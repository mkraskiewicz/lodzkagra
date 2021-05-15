import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LodzkagraSharedModule } from '../shared/shared.module';

import { RANKING_ROUTE, RankingComponent } from './';

@NgModule({
  imports: [LodzkagraSharedModule, RouterModule.forRoot([RANKING_ROUTE], { useHash: true })],
  declarations: [RankingComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class LodzkagraAppRankingModule {}
