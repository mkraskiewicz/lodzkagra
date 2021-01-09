import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LodzkagraSharedModule } from '../shared/shared.module';

import { ZADANIA_ROUTE, ZadaniaComponent } from './';

@NgModule({
  imports: [LodzkagraSharedModule, RouterModule.forRoot([ZADANIA_ROUTE], { useHash: true })],
  declarations: [ZadaniaComponent],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class LodzkagraAppZadaniaModule {}
