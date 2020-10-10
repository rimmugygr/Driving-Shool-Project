import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { StudentsListState, StudentsListStateModel } from './students-list.state';
import { StudentsListAction } from './students-list.actions';

describe('StudentsList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([StudentsListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));

  it('should create an action and add an item', () => {
    const expected: StudentsListStateModel = {
      items: ['item-1']
    };
    store.dispatch(new StudentsListAction('item-1'));
    const actual = store.selectSnapshot(StudentsListState.getState);
    expect(actual).toEqual(expected);
  });

});
