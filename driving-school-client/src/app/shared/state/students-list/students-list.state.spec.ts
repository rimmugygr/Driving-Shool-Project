import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { StudentsListState } from './students-list.state';

describe('StudentsList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([StudentsListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));


});
