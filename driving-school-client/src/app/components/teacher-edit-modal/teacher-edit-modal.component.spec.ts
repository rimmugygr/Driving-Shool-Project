import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherEditModalComponent } from './teacher-edit-modal.component';

describe('TeacherEditModalComponent', () => {
  let component: TeacherEditModalComponent;
  let fixture: ComponentFixture<TeacherEditModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherEditModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherEditModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
