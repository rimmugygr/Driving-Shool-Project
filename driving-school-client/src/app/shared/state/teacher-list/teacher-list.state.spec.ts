import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { TeacherListState, TeacherListStateModel } from './teacher-list.state';
import { TeacherListAction } from './teacher-list.actions';

describe('TeacherList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([TeacherListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));

  it('should create an action and add an item', () => {
    const expected: TeacherListStateModel = {
      items: ['item-1']
    };
    store.dispatch(new TeacherListAction('item-1'));
    const actual = store.selectSnapshot(TeacherListState.getState);
    expect(actual).toEqual(expected);
  });

});
