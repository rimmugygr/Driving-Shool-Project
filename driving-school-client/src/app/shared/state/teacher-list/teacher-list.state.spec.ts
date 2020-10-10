import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { TeacherListState, TeacherListStateModel } from './teacher-list.state';
describe('TeacherList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([TeacherListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));


});
