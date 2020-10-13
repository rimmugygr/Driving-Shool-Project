import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { ProfilesStudentsListState } from './profiles-students-list-state.service';

describe('StudentsList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([ProfilesStudentsListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));


});
