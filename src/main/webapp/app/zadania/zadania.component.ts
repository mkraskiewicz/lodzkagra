import { Component, OnDestroy, OnInit } from '@angular/core';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from '../entities/task/task.service';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TaskDeleteDialogComponent } from 'app/entities/task/task-delete-dialog.component';
@Component({
  selector: 'jhi-zadania',
  templateUrl: './zadania.component.html',
  styleUrls: ['zadania.component.scss'],
})
export class ZadaniaComponent implements OnInit, OnDestroy {
  tasks?: ITask[];
  eventSubscriber?: Subscription;
  wrong: Boolean = false;

  constructor(protected taskService: TaskService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.taskService.query2().subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body || []));
  }

  ngOnInit(): void {
    this.wrong = false;
    this.loadAll();
    this.registerChangeInTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('taskListModification', () => this.loadAll());
  }

  delete(task: ITask): void {
    const modalRef = this.modalService.open(TaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.task = task;
  }

  sendAnswer(value: string, task: ITask): void {
    this.subscribeToSaveResponse(this.taskService.sendAnswer(value, task));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>): void {
    result.subscribe(
      result2 => this.onSaveSuccess(result2.body),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(body: ITask | null): void {
    if (body?.isCompleted === true) {
      window.location.reload();
    } else {
      this.wrong = true;
    }
  }

  protected onSaveError(): void {}
}
