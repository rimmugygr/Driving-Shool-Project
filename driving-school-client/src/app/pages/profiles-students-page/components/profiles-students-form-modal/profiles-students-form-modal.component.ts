import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {FORM_PAGE_MODE} from '../../../../shared/model/FormPageMode';
import {Store} from '@ngxs/store';
import {Student} from '../../../../shared/model/Student';
import {CreateStudent, DeleteStudent, UpdateStudent} from '../../../../shared/state/profiles-students-list/profiles-students-list.actions';
import {ProfilesStudentsListState} from '../../../../shared/state/profiles-students-list/profiles-students-list-state.service';

@Component({
  selector: 'app-teacher-form-modal',
  templateUrl: './profiles-students-form-modal.component.html',
  styleUrls: ['./profiles-students-form-modal.component.css']
})
export class ProfilesStudentsFormModalComponent implements OnInit {
  @Input() pageMode: FORM_PAGE_MODE;
  @Input() studentId;
  @Input() userId;
  student: Student;
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
      this.store.dispatch(new DeleteStudent({student: this.form.getRawValue()}));
    }
    if (this.form.valid) {
      if (this.isCreateMode()) {
        this.store.dispatch(new CreateStudent({student: this.form.value}));
        this.modal.close();
      }
      if (this.isEditMode()) {
        this.store.dispatch(new UpdateStudent({student: this.form.getRawValue()}));
        this.modal.close();
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
      address: new FormControl(null, Validators.required),
      phone: new FormControl(null, Validators.required),
      status: new FormControl(null, Validators.required),
      hours: new FormControl({value: null, disabled: true}),
      email: new FormControl(null, [Validators.email, Validators.required]),
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
      this.student = this.store.selectSnapshot(ProfilesStudentsListState.getStudentById)(this.studentId);
      this.form.patchValue({...this.student});
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
