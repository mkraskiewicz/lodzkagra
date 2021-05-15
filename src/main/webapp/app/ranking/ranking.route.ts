import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { RankingComponent } from './ranking.component';

export const RANKING_ROUTE: Route = {
  path: 'ranking',
  component: RankingComponent,
  data: {
    authorities: [],
    pageTitle: 'ranking.title',
  },
  canActivate: [UserRouteAccessService],
};
