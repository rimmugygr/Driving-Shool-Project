import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ITeacher} from '../../../../shared/model/Teacher';
import {FORM_PAGE_MODE} from '../../../../shared/model/FormPageMode';
import {Store} from '@ngxs/store';
import {CreateTeacher, DeleteTeacher, UpdateTeacher} from '../../../../shared/state/profiles-teachers-list/profiles-teachers-list.actions';
import {ProfilesTeachersListState} from '../../../../shared/state/profiles-teachers-list/profiles-teachers-list-state.service';

@Component({
  selector: 'app-teacher-form-modal',
  templateUrl: './profiles-teachers-form-modal.component.html',
  styleUrls: ['./profiles-teachers-form-modal.component.css']
})
export class ProfilesTeachersFormModalComponent implements OnInit {
  @Input() pageMode: FORM_PAGE_MODE;
  @Input() teacherId;
  @Input() userId;
  teacher: ITeacher;
  hide = true;
  form: FormGroup;
  message = '';

  constructor(public modal: NgbActiveModal,
              private store: Store) { }

  ngOnInit(): void {
    this.initForm();
  }

  submitForm(): void {
    if (this.isDeleteMode()) {
      this.store.dispatch(new DeleteTeacher({teacher: this.form.getRawValue()}));
    }
    if (this.form.valid) {
      if (this.isCreateMode()) {
        this.store.dispatch(new CreateTeacher({teacher: this.form.value}));
      }
      if (this.isEditMode()) {
        this.store.dispatch(new UpdateTeacher({teacher: this.form.getRawValue()}));
      }
    } else {
      this.message = 'Pleas fill all form !';
    }
  }

  private initForm(): void {
    this.form = new FormGroup({
      id: new FormControl({value: null, disabled: true}),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      user: new FormGroup ({
        id: new FormControl({value: null, disabled: true}),
        username: new FormControl(null, Validators.required),
        password: new FormControl(null, Validators.required),
        createDate: new FormControl({value: null, disabled: true}),
        updateDate: new FormControl( {value: null, disabled: true}),
      }),
      createDate: new FormControl( {value: null, disabled: true}),
      updateDate: new FormControl( {value: null, disabled: true}),
    });
    if (this.isEditMode() || this.isDetailsMode() || this.isDeleteMode()) {
      this.teacher = this.store.selectSnapshot(ProfilesTeachersListState.getTeacherById)(this.teacherId);
      this.form.patchValue({...this.teacher});
    }
    if (this.isDetailsMode()) {
      this.form.disable();
    }

  }

  isEditMode(): boolean {
    return this.pageMode === FORM_PAGE_MODE.EDIT;
  }

  isDetailsMode(): boolean {
    return this.pageMode === FORM_PAGE_MODE.DETAILS;
  }

  isCreateMode(): boolean {
    return this.pageMode === FORM_PAGE_MODE.CREATE;
  }

  isDeleteMode(): boolean {
    return this.pageMode === FORM_PAGE_MODE.DELETE;
  }
}
