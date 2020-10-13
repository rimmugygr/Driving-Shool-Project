import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { ProfilesTeachersListState, ProfilesTeachersListStateModel } from './profiles-teachers-list-state.service';
describe('TeacherList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([ProfilesTeachersListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));


});
