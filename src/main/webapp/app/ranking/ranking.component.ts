import { Component, OnInit } from '@angular/core';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from '../entities/task/task.service';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TaskDeleteDialogComponent } from 'app/entities/task/task-delete-dialog.component';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
@Component({
  selector: 'jhi-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['ranking.component.scss'],
})
export class RankingComponent implements OnInit {
  tasks?: ITask[] = [];
  eventSubscriber?: Subscription;
  wrong: Boolean = false;
  account: Account | null = null;
  authSubscription?: Subscription;
  constructor(
    protected taskService: TaskService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private accountService: AccountService,
    private loginModalService: LoginModalService
  ) {}

  loadAll(): void {
    this.taskService.query2().subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body || []));
  }

  ngOnInit(): void {
    this.wrong = false;
    this.loadAll();
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }
}
