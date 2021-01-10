import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ZadaniaComponent } from './zadania.component';

export const ZADANIA_ROUTE: Route = {
  path: 'zadania',
  component: ZadaniaComponent,
  data: {
    authorities: [],
    pageTitle: 'Zadania lodzkiej gry',
  },
  canActivate: [UserRouteAccessService],
};
