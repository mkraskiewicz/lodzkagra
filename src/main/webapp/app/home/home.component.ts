import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
  providers: [NgbCarouselConfig],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  //images = [700, 800, 807].map((n) => `https://picsum.photos/id/${n}/900/500`);
  //  images = ['https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/118254675_10157925118814864_798804744327974043_o.jpg?_nc_cat=109&ccb=2&_nc_sid=8bfeb9&_nc_ohc=-kOsaUpmUqYAX9PIeuu&_nc_ht=scontent-waw1-1.xx&oh=358215d9b2fd23f28d670e9e723f355d&oe=601C5003','https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/104312318_10157755419544864_3780709617909700352_o.jpg?_nc_cat=102&ccb=2&_nc_sid=730e14&_nc_ohc=1Y3ko6w8YggAX8nf1El&_nc_ht=scontent-waw1-1.xx&oh=1f64b5ae9d856c2a88a2d6905e0f5522&oe=601C19D7','https://scontent-waw1-1.xx.fbcdn.net/v/t1.0-9/69622008_10156910353564864_8326080414405885952_o.jpg?_nc_cat=101&ccb=2&_nc_sid=730e14&_nc_ohc=YCtzXzhthwsAX-43oRX&_nc_ht=scontent-waw1-1.xx&oh=c962970fc25da9624f45d0940e583f18&oe=601AE636']
  images = [
    '../../content/images/lodz1.png',
    '../../content/images/lodz2.png',
    '../../content/images/lodz3.png',
    '../../content/images/lodz4.png',
  ];
  constructor(private accountService: AccountService, private loginModalService: LoginModalService, config: NgbCarouselConfig) {
    config.interval = 2000;
    config.keyboard = true;
    config.pauseOnHover = true;
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
